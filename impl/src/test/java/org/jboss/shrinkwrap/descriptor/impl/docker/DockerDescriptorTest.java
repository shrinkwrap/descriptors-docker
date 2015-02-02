/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.jboss.shrinkwrap.descriptor.impl.docker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.DescriptorImporter;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.FromInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.OnBuildInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.RunInstruction;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link DockerDescriptorImpl}
 *
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class DockerDescriptorTest
{

    @Test
    public void testShrinkWrapDescriptorsLookup()
    {
        DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
        Assert.assertNotNull(descriptor);
        assertThat(descriptor, instanceOf(DockerDescriptorImpl.class));
    }

    @Test
    public void testInstructions()
    {
        DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
        Assert.assertEquals("FROM jbossforge", descriptor.from().name("jbossforge").toString());
        Assert.assertEquals("USER George", descriptor.user().name("George").toString());
        assertThat(descriptor.getInstructions().size(), equalTo(2));
        String output = descriptor.exportAsString();
        Assert.assertEquals("FROM jbossforge\nUSER George", output);
    }

    @Test
    public void testParsing() throws Exception
    {
        String dockerFileContents = "FROM jbossforge\nUSER George\nRUN cmd.exe /n";
        DescriptorImporter<DockerDescriptor> importer = Descriptors.importAs(DockerDescriptor.class);
        DockerDescriptor descriptor = importer.fromString(dockerFileContents);
        Assert.assertNotNull(descriptor);
        Assert.assertEquals(3, descriptor.getInstructions().size());
        Assert.assertNotNull(descriptor.getFrom());
        Assert.assertNotNull(descriptor.getUser());
        Assert.assertEquals(1, descriptor.getAllRun().size());
        Assert.assertEquals("jbossforge", descriptor.getFrom().getName());
        Assert.assertEquals("George", descriptor.getUser().getName());
        List<String> parameters = descriptor.getAllRun().get(0).getParameters();
        Assert.assertEquals("cmd.exe", parameters.get(0));
        Assert.assertEquals("/n", parameters.get(1));
    }

    @Test
    public void testCommentParsing() throws Exception
    {
        String dockerFileContents = "#Awesome\n\nFROM jbossforge\nUSER George\nRUN [\"cmd.exe\",\"/n\"]";
        DescriptorImporter<DockerDescriptor> importer = Descriptors.importAs(DockerDescriptor.class);
        DockerDescriptor descriptor = importer.fromString(dockerFileContents);
        Assert.assertEquals(5, descriptor.getInstructions().size());
        Assert.assertEquals(dockerFileContents, descriptor.exportAsString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNestedOnBuildIsInvalid() throws Exception
    {
        DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
        descriptor.onBuild().instruction(OnBuildInstruction.class);
    }
    @Test
    public void testOnBuild() throws Exception
    {
        DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
        descriptor.onBuild().instruction(RunInstruction.class).parameters("cd","foo");
        Assert.assertEquals("ONBUILD RUN [\"cd\",\"foo\"]", descriptor.exportAsString());
    }

}

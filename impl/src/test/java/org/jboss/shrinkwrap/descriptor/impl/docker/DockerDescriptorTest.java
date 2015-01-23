/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.DescriptorImporter;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
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

}

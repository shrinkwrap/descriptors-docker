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
   public void testShrinkWrapDescriptorsFrom()
   {
      DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
      descriptor.from().name("jbossforge");
      descriptor.user().name("George");
      assertThat(descriptor.getInstructions().size(), equalTo(2));
   }

   @Test
   public void testOnBuildShouldNotAcceptOnBuild()
   {

   }

}

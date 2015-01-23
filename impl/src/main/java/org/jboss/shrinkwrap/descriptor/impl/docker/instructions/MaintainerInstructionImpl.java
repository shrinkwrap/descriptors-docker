/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.MaintainerInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class MaintainerInstructionImpl extends AbstractDockerInstruction implements MaintainerInstruction
{

   private String name;

   /**
    * @param descriptor
    */
   public MaintainerInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public MaintainerInstruction name(String name)
   {
      this.name = name;
      return this;
   }

   @Override
   public String getName()
   {
      return name;
   }
}

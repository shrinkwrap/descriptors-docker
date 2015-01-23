/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.WorkdirInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class WorkdirInstructionImpl extends AbstractDockerInstruction implements WorkdirInstruction
{

   public WorkdirInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public String getWorkdir()
   {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.jboss.shrinkwrap.descriptor.api.docker.instruction.WorkdirInstruction#setWorkdir()
    */
   @Override
   public WorkdirInstruction setWorkdir()
   {
      return null;
   }

}

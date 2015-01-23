/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.EntrypointInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class EntrypointInstructionImpl extends AbstractDockerInstruction implements EntrypointInstruction
{

   public EntrypointInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.jboss.shrinkwrap.descriptor.api.docker.instruction.EntrypointInstruction#getEntrypoint()
    */
   @Override
   public List<String> getEntrypoint()
   {
      // TODO Auto-generated method stub
      return null;
   }

}

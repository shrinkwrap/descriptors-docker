/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.ExposeInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class ExposeInstructionImpl extends AbstractDockerInstruction implements ExposeInstruction
{

   /**
    * @param descriptor
    */
   public ExposeInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public List<Integer> getPorts()
   {
      // TODO Auto-generated method stub
      return null;
   }

}

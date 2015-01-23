/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.VolumeInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class VolumeInstructionImpl extends AbstractDockerInstruction implements VolumeInstruction
{
   public VolumeInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
      // TODO Auto-generated constructor stub
   }

}
/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.VolumeInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class VolumeInstructionImpl extends AbstractDockerInstruction implements VolumeInstruction
{
   private String name;

   public VolumeInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public VolumeInstruction name(String name)
   {
      this.name = name;
      return this;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (name == null || name.isEmpty())
         throw new IllegalStateException("Name is null or empty");
      writer.append("VOLUME ").append(name);
   }

}

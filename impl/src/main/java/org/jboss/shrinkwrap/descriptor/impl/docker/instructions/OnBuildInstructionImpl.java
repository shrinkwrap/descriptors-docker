/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.OnBuildInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class OnBuildInstructionImpl extends AbstractDockerInstruction implements OnBuildInstruction
{
   private DockerInstruction instruction;

   public OnBuildInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public OnBuildInstruction instruction(DockerInstruction instruction)
   {
      this.instruction = instruction;
      return this;
   }

   @Override
   public DockerInstruction getInstruction()
   {
      return instruction;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (instruction == null)
         throw new IllegalStateException("instruction is empty");
      writer.append("ONBUILD ").append(instruction.toString());
   }

   @Override
   public void read(String line)
   {
      DockerInstruction instruction = DockerInstructions.create(line.substring(9), up());
      instruction(instruction);
   }
}

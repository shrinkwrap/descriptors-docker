/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.RunInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class RunInstructionImpl extends AbstractDockerInstruction implements RunInstruction
{
   private List<String> parameters = new ArrayList<>();

   public RunInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public List<String> getParameters()
   {
      return parameters;
   }

   @Override
   public RunInstruction parameters(String... commands)
   {
      parameters.addAll(Arrays.asList(commands));
      return this;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (parameters.isEmpty())
      {
         throw new IllegalStateException("Parameters should not be empty");
      }
      writer.append("RUN ");
      if (parameters.size() > 1)
      {
         writer.append("[");
         for (int i = 0; i < parameters.size(); i++)
         {
            writer.append("\"");
            writer.append(parameters.get(i));
            writer.append("\"");
            if (i != parameters.size() - 1)
               writer.append(",");
         }
         writer.append("]");
      }
      else
      {
         writer.append(parameters.get(0));
      }
   }

   @Override
   public void read(String line)
   {
      // TODO: Review
      String[] split = line.substring(4).split(" ");
      parameters(split);
   }
}

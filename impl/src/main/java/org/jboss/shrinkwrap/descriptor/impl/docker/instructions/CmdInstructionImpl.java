/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CmdInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
class CmdInstructionImpl extends AbstractDockerInstruction implements CmdInstruction
{
   private List<String> parameters = new ArrayList<>();

   public CmdInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public CmdInstruction parameters(String... parameters)
   {
      this.parameters.addAll(Arrays.asList(parameters));
      return this;
   }

   @Override
   public List<String> getParameters()
   {
      return parameters;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (parameters.isEmpty())
      {
         throw new IllegalStateException("Parameters should not be empty");
      }
      writer.append("CMD ");
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
      String[] split = line.substring(4).split(" ");
      parameters(split);
   }
}
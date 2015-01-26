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
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.ExposeInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class ExposeInstructionImpl extends AbstractDockerInstruction implements ExposeInstruction
{
   private List<Integer> ports = new ArrayList<>();

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
      return ports;
   }

   @Override
   public ExposeInstruction ports(Integer... ports)
   {
      this.ports.addAll(Arrays.asList(ports));
      return this;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (ports.isEmpty())
      {
         throw new IllegalStateException("Ports should not be empty");
      }
      writer.append("EXPOSE ");
      for (int i = 0; i < ports.size(); i++)
      {
         writer.append(String.valueOf(ports.get(i)));
         if (i != ports.size() - 1)
         {
            writer.append(' ');
         }
      }
   }

   @Override
   public void read(String line)
   {
      String[] split = line.substring(8).split(" ");
      List<Integer> ports = new ArrayList<>();
      for (String port : split)
      {
         ports.add(Integer.valueOf(port));
      }
      ports(ports.toArray(new Integer[ports.size()]));
   }

}

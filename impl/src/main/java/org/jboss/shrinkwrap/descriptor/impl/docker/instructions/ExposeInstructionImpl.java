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

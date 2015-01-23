/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CopyInstruction;

/**
 * Implementation of {@link CopyInstruction}
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class CopyInstructionImpl extends AbstractDockerInstruction implements CopyInstruction
{
   private String source;
   private String destination;

   public CopyInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public CopyInstruction source(String source)
   {
      this.source = source;
      return this;
   }

   @Override
   public CopyInstruction destination(String dest)
   {
      this.destination = dest;
      return this;
   }

   @Override
   public String getSource()
   {
      return source;
   }

   @Override
   public String getDestination()
   {
      return destination;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (source == null || source.isEmpty())
         throw new IllegalStateException("Source is null or empty");
      if (destination == null || destination.isEmpty())
         throw new IllegalStateException("Destination is null or empty");
      writer.append("COPY ").append(source).append(" ").append(destination);
   }

   @Override
   public void read(String line)
   {
      String[] split = line.substring(6).split(" ");
      source(split[1]).destination(split[2]);
   }
}

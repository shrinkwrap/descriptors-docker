/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.WorkdirInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class WorkdirInstructionImpl extends AbstractDockerInstruction implements WorkdirInstruction
{
   private String path;

   public WorkdirInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public String getPath()
   {
      return path;
   }

   @Override
   public WorkdirInstruction path(String path)
   {
      this.path = path;
      return this;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (path == null || path.isEmpty())
         throw new IllegalStateException("Workdir is null or empty");
      writer.append("WORKDIR ").append(path);
   }

   @Override
   public void read(String line)
   {
      path(line.substring(9));
   }

}

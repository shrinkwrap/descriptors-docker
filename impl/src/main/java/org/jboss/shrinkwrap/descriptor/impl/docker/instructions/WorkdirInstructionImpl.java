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

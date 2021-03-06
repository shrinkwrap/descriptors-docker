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
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.MaintainerInstruction;

/**
 * {@link MaintainerInstruction} implementation
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class MaintainerInstructionImpl extends AbstractDockerInstruction implements MaintainerInstruction
{

   private String name;

   public MaintainerInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public MaintainerInstruction name(String name)
   {
      this.name = name;
      return this;
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (name == null || name.isEmpty())
         throw new IllegalStateException("name is empty or null");
      writer.append("MAINTAINER ").append(name);
   }

   @Override
   public void read(String line)
   {
      name(line.substring(11));
   }

}

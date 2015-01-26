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

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;

/**
 * Abstract class for {@link DockerInstruction}s
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public abstract class AbstractDockerInstruction implements DockerInstruction
{
   private final DockerDescriptor descriptor;

   protected AbstractDockerInstruction(DockerDescriptor descriptor)
   {
      this.descriptor = descriptor;
   }

   @Override
   public DockerDescriptor up()
   {
      return descriptor;
   }

   public String toString()
   {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try (PrintWriter writer = new PrintWriter(baos, true))
      {
         export(writer);
      }
      return baos.toString();
   }
}

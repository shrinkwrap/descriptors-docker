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
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.EnvInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class EnvInstructionImpl extends AbstractDockerInstruction implements EnvInstruction
{
   private String key;
   private String value;

   public EnvInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public EnvInstruction key(String key)
   {
      this.key = key;
      return this;
   }

   @Override
   public EnvInstruction value(String value)
   {
      this.value = value;
      return this;
   }

   @Override
   public String getKey()
   {
      return key;
   }

   @Override
   public String getValue()
   {
      return value;
   }

   @Override
   public void export(PrintWriter writer)
   {
      if (key == null || key.isEmpty())
         throw new IllegalStateException("key is null or empty");
      if (value == null || value.isEmpty())
         throw new IllegalStateException("value is null or empty");
      writer.append("ENV ");
      writer.append(key);
      writer.append(' ');
      writer.append(value);
   }

   @Override
   public void read(String line)
   {
      String[] split = line.substring(5).split(" |=");
      key(split[0]).value(split[1]);
   }
}

/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
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

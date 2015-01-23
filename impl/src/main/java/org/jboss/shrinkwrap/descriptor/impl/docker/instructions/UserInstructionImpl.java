/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.UserInstruction;

/**
 * Implementation for {@link UserInstruction}
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class UserInstructionImpl extends AbstractDockerInstruction implements UserInstruction
{
   private String name;

   public UserInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public UserInstruction name(String name)
   {
      this.name = name;
      return this;
   }

   @Override
   protected void export(PrintWriter writer)
   {
      writer.append("USER ").append(name);
   }
}

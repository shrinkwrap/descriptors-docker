/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
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
      export(new PrintWriter(baos, true));
      return baos.toString();
   }

   protected abstract void export(PrintWriter writer);
}

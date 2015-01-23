/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.DescriptorExportException;
import org.jboss.shrinkwrap.descriptor.api.DescriptorExporter;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public enum DockerDescriptorExporter implements DescriptorExporter<DockerDescriptor>
{
   INSTANCE;

   @Override
   public void to(DockerDescriptor descriptor, OutputStream out) throws DescriptorExportException,
            IllegalArgumentException
   {
      try (PrintWriter writer = new PrintWriter(out, true))
      {
         for (DockerInstruction dockerInstruction : descriptor.getInstructions())
         {
            dockerInstruction.export(writer);
            writer.println();
         }
      }
   }
}

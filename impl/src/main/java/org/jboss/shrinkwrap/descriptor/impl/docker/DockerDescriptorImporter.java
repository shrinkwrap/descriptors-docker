/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jboss.shrinkwrap.descriptor.api.DescriptorImportException;
import org.jboss.shrinkwrap.descriptor.api.DescriptorImporter;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;
import org.jboss.shrinkwrap.descriptor.impl.docker.instructions.DockerInstructions;
import org.jboss.shrinkwrap.descriptor.spi.DescriptorImporterBase;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class DockerDescriptorImporter<T extends DockerDescriptor> extends DescriptorImporterBase<T>
         implements
         DescriptorImporter<T>
{
   /**
    * Creates a new instance representing the specified backing model type
    *
    * @param The type of the backing object model for the descriptor
    * @throws IllegalArgumentException If the model type is not specified
    * @throws IllegalArgumentException If the descriptorName not specified
    */
   public DockerDescriptorImporter(final Class<T> endUserViewImplType,
            final String descriptorName)
            throws IllegalArgumentException
   {
      super(endUserViewImplType, descriptorName);
   }

   @SuppressWarnings("unchecked")
   @Override
   public T fromStream(InputStream in, boolean close) throws IllegalArgumentException, DescriptorImportException
   {
      DockerDescriptor descriptor = Descriptors.create(DockerDescriptor.class);
      if (close)
      {
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
         {
            String line;
            while ((line = reader.readLine()) != null)
            {
               DockerInstruction instruction = DockerInstructions.create(line, descriptor);
               descriptor.addInstruction(instruction);
            }
         }
         catch (IOException e)
         {
            throw new DescriptorImportException("Could not import descriptor", e);
         }
      }
      else
      {
         try
         {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null)
            {
               DockerInstruction instruction = DockerInstructions.create(line, descriptor);
               descriptor.addInstruction(instruction);
            }
         }
         catch (IOException e)
         {
            throw new DescriptorImportException("Could not import descriptor", e);
         }

      }
      return (T) descriptor;
   }
}

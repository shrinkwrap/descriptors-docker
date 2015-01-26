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

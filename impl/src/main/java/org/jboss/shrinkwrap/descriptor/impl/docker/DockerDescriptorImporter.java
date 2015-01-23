/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import java.io.InputStream;

import org.jboss.shrinkwrap.descriptor.api.DescriptorImportException;
import org.jboss.shrinkwrap.descriptor.api.DescriptorImporter;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
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

   @Override
   public T fromStream(InputStream in, boolean close) throws IllegalArgumentException, DescriptorImportException
   {
      return null;
   }

}

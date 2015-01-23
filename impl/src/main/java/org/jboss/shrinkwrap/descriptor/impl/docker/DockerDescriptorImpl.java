/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import java.io.OutputStream;

import org.jboss.shrinkwrap.descriptor.api.DescriptorExportException;
import org.jboss.shrinkwrap.descriptor.api.DescriptorExporter;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.spi.DescriptorImplBase;

/**
 * Implementation of the {@link DockerDescriptor} interface
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class DockerDescriptorImpl extends DescriptorImplBase<DockerDescriptor> implements DockerDescriptor
{
   public DockerDescriptorImpl(String content)
   {
      super(content);
   }

   @Override
   public void exportTo(OutputStream output) throws DescriptorExportException, IllegalArgumentException
   {
      if (output == null)
      {
         throw new IllegalArgumentException("Can not export to null stream");
      }
      getExporter().to(this, output);
   }

   @Override
   protected final DescriptorExporter<DockerDescriptor> getExporter()
   {
      return DockerDescriptorExporter.INSTANCE;
   }
}

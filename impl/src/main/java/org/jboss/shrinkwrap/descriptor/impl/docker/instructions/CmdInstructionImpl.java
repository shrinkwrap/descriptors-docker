/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CmdInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
class CmdInstructionImpl extends AbstractDockerInstruction implements CmdInstruction
{
   private List<String> parameters = new ArrayList<>();

   public CmdInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public List<String> getParameters()
   {
      return parameters;
   }

   @Override
   public void export(PrintWriter writer)
   {
      // writer.append(getInstruction()).append(parameters);
   }
}
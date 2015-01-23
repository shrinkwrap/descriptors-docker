/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CommentInstruction;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class CommentInstructionImpl extends AbstractDockerInstruction implements CommentInstruction
{

   private String text;

   public CommentInstructionImpl(DockerDescriptor descriptor)
   {
      super(descriptor);
   }

   @Override
   public CommentInstruction text(String text)
   {
      this.text = text;
      return this;
   }

   @Override
   public String getText()
   {
      return text;
   }

   @Override
   public void export(PrintWriter writer)
   {
      writer.append("# ").append(" ").append(text);
   }

}

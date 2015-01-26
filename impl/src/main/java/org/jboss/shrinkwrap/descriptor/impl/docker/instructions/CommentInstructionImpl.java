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
      if (text != null && !text.isEmpty())
         writer.append("#").append(text);
   }

   @Override
   public void read(String line)
   {
      int idx = line.startsWith("#") ? 1 : 0;
      text(line.substring(idx));
   }

}

/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker.instructions;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.AddInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CmdInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CommentInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.CopyInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.EntrypointInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.EnvInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.ExposeInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.FromInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.MaintainerInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.OnBuildInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.RunInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.UserInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.VolumeInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.WorkdirInstruction;

/**
 * An enumeration with all the supported Docker instructions
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
@SuppressWarnings("unchecked")
public enum DockerInstructions
{
   ADD("ADD")
   {
      @Override
      public AddInstruction create(DockerDescriptor descriptor)
      {
         return new AddInstructionImpl(descriptor);
      }
   },
   CMD("CMD")
   {
      @Override
      public CmdInstruction create(DockerDescriptor descriptor)
      {
         return new CmdInstructionImpl(descriptor);
      }
   },
   COMMENT("#")
   {
      @Override
      public CommentInstruction create(DockerDescriptor descriptor)
      {
         return new CommentInstructionImpl(descriptor);
      }
   },
   COPY("COPY")
   {
      @Override
      public CopyInstruction create(DockerDescriptor descriptor)
      {
         return new CopyInstructionImpl(descriptor);
      }
   },
   ENTRYPOINT("ENTRYPOINT")
   {
      @Override
      public EntrypointInstruction create(DockerDescriptor descriptor)
      {
         return new EntrypointInstructionImpl(descriptor);
      }
   },
   ENV("ENV")
   {
      @Override
      public EnvInstruction create(DockerDescriptor descriptor)
      {
         return new EnvInstructionImpl(descriptor);
      }
   },
   EXPOSE("EXPOSE")
   {
      @Override
      public ExposeInstruction create(DockerDescriptor descriptor)
      {
         return new ExposeInstructionImpl(descriptor);
      }
   },
   FROM("FROM")
   {
      @Override
      public FromInstruction create(DockerDescriptor descriptor)
      {
         return new FromInstructionImpl(descriptor);
      }
   },
   MAINTAINER("MAINTAINER")
   {
      @Override
      public MaintainerInstruction create(DockerDescriptor descriptor)
      {
         return new MaintainerInstructionImpl(descriptor);
      }
   },
   ONBUILD("ONBUILD")
   {
      @Override
      public OnBuildInstruction create(DockerDescriptor descriptor)
      {
         return new OnBuildInstructionImpl(descriptor);
      }
   },
   RUN("RUN")
   {
      @Override
      public RunInstruction create(DockerDescriptor descriptor)
      {
         return new RunInstructionImpl(descriptor);
      }
   },
   USER("USER")
   {
      @Override
      public UserInstruction create(DockerDescriptor descriptor)
      {
         return new UserInstructionImpl(descriptor);
      }
   },
   VOLUME("VOLUME")
   {
      @Override
      public VolumeInstruction create(DockerDescriptor descriptor)
      {
         return new VolumeInstructionImpl(descriptor);
      }
   },
   WORKDIR("WORKDIR")
   {
      @Override
      public WorkdirInstruction create(DockerDescriptor descriptor)
      {
         return new WorkdirInstructionImpl(descriptor);
      }
   };

   private final String instruction;

   private DockerInstructions(String instruction)
   {
      this.instruction = instruction;
   }

   public abstract <T extends DockerInstruction> T create(DockerDescriptor descriptor);

   public static DockerInstruction create(String input, DockerDescriptor descriptor)
   {
      for (DockerInstructions inst : values())
      {
         if (inst.handles(input))
         {
            return inst.create(descriptor);
         }
      }
      throw new IllegalArgumentException("Input not recognized: " + input);
   }

   public boolean handles(String input)
   {
      return input.toUpperCase().startsWith(getInstruction());
   }

   /**
    * @return the instruction
    */
   public String getInstruction()
   {
      return instruction;
   }

}

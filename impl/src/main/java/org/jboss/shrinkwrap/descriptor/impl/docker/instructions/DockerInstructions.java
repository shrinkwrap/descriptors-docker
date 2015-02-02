/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
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

    COMMENT("#")
            {
                @Override
                public CommentInstruction create(DockerDescriptor descriptor)
                {
                    return new CommentInstructionImpl(descriptor);
                }

                @Override
                public boolean handles(String input)
                {
                    return input.trim().isEmpty() || input.startsWith("#");
                }

                @Override
                public Class<CommentInstruction> getInstructionClass()
                {
                    return CommentInstruction.class;
                }
            },
    ADD("ADD")
            {
                @Override
                public AddInstruction create(DockerDescriptor descriptor)
                {
                    return new AddInstructionImpl(descriptor);
                }

                @Override
                public Class<AddInstruction> getInstructionClass()
                {
                    return AddInstruction.class;
                }
            },
    CMD("CMD")
            {
                @Override
                public CmdInstruction create(DockerDescriptor descriptor)
                {
                    return new CmdInstructionImpl(descriptor);
                }

                @Override
                public Class<CmdInstruction> getInstructionClass()
                {
                    return CmdInstruction.class;
                }
            },
    COPY("COPY")
            {
                @Override
                public CopyInstruction create(DockerDescriptor descriptor)
                {
                    return new CopyInstructionImpl(descriptor);
                }

                @Override
                public Class<CopyInstruction> getInstructionClass()
                {
                    return CopyInstruction.class;
                }

            },
    ENTRYPOINT("ENTRYPOINT")
            {
                @Override
                public EntrypointInstruction create(DockerDescriptor descriptor)
                {
                    return new EntrypointInstructionImpl(descriptor);
                }

                @Override
                public Class<EntrypointInstruction> getInstructionClass()
                {
                    return EntrypointInstruction.class;
                }

            },
    ENV("ENV")
            {
                @Override
                public EnvInstruction create(DockerDescriptor descriptor)
                {
                    return new EnvInstructionImpl(descriptor);
                }

                @Override
                public Class<EnvInstruction> getInstructionClass()
                {
                    return EnvInstruction.class;
                }

            },
    EXPOSE("EXPOSE")
            {
                @Override
                public ExposeInstruction create(DockerDescriptor descriptor)
                {
                    return new ExposeInstructionImpl(descriptor);
                }

                @Override
                public Class<ExposeInstruction> getInstructionClass()
                {
                    return ExposeInstruction.class;
                }

            },
    FROM("FROM")
            {
                @Override
                public FromInstruction create(DockerDescriptor descriptor)
                {
                    return new FromInstructionImpl(descriptor);
                }

                @Override
                public Class<FromInstruction> getInstructionClass()
                {
                    return FromInstruction.class;
                }

            },
    MAINTAINER("MAINTAINER")
            {
                @Override
                public MaintainerInstruction create(DockerDescriptor descriptor)
                {
                    return new MaintainerInstructionImpl(descriptor);
                }

                @Override
                public Class<MaintainerInstruction> getInstructionClass()
                {
                    return MaintainerInstruction.class;
                }

            },
    ONBUILD("ONBUILD")
            {
                @Override
                public OnBuildInstruction create(DockerDescriptor descriptor)
                {
                    return new OnBuildInstructionImpl(descriptor);
                }

                @Override
                public Class<OnBuildInstruction> getInstructionClass()
                {
                    return OnBuildInstruction.class;
                }

            },
    RUN("RUN")
            {
                @Override
                public RunInstruction create(DockerDescriptor descriptor)
                {
                    return new RunInstructionImpl(descriptor);
                }

                @Override
                public Class<RunInstruction> getInstructionClass()
                {
                    return RunInstruction.class;
                }

            },
    USER("USER")
            {
                @Override
                public UserInstruction create(DockerDescriptor descriptor)
                {
                    return new UserInstructionImpl(descriptor);
                }

                @Override
                public Class<UserInstruction> getInstructionClass()
                {
                    return UserInstruction.class;
                }

            },
    VOLUME("VOLUME")
            {
                @Override
                public VolumeInstruction create(DockerDescriptor descriptor)
                {
                    return new VolumeInstructionImpl(descriptor);
                }

                @Override
                public Class<VolumeInstruction> getInstructionClass()
                {
                    return VolumeInstruction.class;
                }

            },
    WORKDIR("WORKDIR")
            {
                @Override
                public WorkdirInstruction create(DockerDescriptor descriptor)
                {
                    return new WorkdirInstructionImpl(descriptor);
                }

                @Override
                public Class<WorkdirInstruction> getInstructionClass()
                {
                    return WorkdirInstruction.class;
                }

            };

    private final String instruction;

    private DockerInstructions(String instruction)
    {
        this.instruction = instruction;
    }

    public abstract <T extends DockerInstruction> T create(DockerDescriptor descriptor);

    public abstract <T extends DockerInstruction> Class<T> getInstructionClass();

    public static DockerInstructions from(Class<? extends DockerInstruction> instructionClass)
    {
        if (instructionClass == null)
        {
            throw new IllegalArgumentException("Instruction class cannot be null");
        }
        for (DockerInstructions inst : values())
        {
            if (inst.getInstructionClass().isAssignableFrom(instructionClass))
            {
                return inst;
            }
        }
        throw new IllegalArgumentException("Instruction class not supported: " + instructionClass.getName());
    }

    public static DockerInstruction create(String input, DockerDescriptor descriptor)
    {
        for (DockerInstructions inst : values())
        {
            if (inst.handles(input))
            {
                DockerInstruction create = inst.create(descriptor);
                create.read(input);
                return create;
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

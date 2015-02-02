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

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.OnBuildInstruction;

/**
 *
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class OnBuildInstructionImpl extends AbstractDockerInstruction implements OnBuildInstruction
{

    private DockerInstruction instruction;

    public OnBuildInstructionImpl(DockerDescriptor descriptor)
    {
        super(descriptor);
    }

    @Override
    public <T extends DockerInstruction> T instruction(Class<T> instruction)
    {
        if (instruction == OnBuildInstruction.class) 
            throw new IllegalArgumentException("Cannot use nested ONBUILD instructions");
        DockerInstructions instructionEnum = DockerInstructions.from(instruction);
        this.instruction = instructionEnum.create(up());
        return (T) this.instruction;
    }

    public void setInstruction(DockerInstruction instruction)
    {
        this.instruction = instruction;
    }

    @Override
    public DockerInstruction getInstruction()
    {
        return instruction;
    }

    @Override
    public void export(PrintWriter writer)
    {
        if (instruction == null)
        {
            throw new IllegalStateException("instruction is empty");
        }
        writer.append("ONBUILD ").append(instruction.toString());
    }

    @Override
    public void read(String line)
    {
        DockerInstruction instruction = DockerInstructions.create(line.substring(9), up());
        setInstruction(instruction);
    }
}

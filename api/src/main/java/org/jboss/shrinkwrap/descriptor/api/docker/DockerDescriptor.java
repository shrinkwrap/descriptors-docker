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

package org.jboss.shrinkwrap.descriptor.api.docker;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.Descriptor;
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
 * This deployment descriptor provides the functionalities as described in the
 * 
 * <a href="https://docs.docker.com/reference/builder/">Dockerfile Reference</a>
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public interface DockerDescriptor extends Descriptor
{
   /**
    * The <code>FROM</code> instruction sets the Base Image for subsequent instructions. As such, a valid Dockerfile
    * must have <code>FROM</code> as its first instruction. The image can be any valid image – it is especially easy to
    * start by pulling an image from the Public Repositories.
    * 
    * @return a {@link FromInstruction} instance
    */
   FromInstruction from();

   /**
    * The <code>FROM</code> instruction sets the Base Image for subsequent instructions. As such, a valid Dockerfile
    * must have <code>FROM</code> as its first instruction. The image can be any valid image – it is especially easy to
    * start by pulling an image from the Public Repositories.
    * 
    * @param name the image name to be used in this <code>FROM</code> instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor from(String name);

   /**
    * @return the current {@link FromInstruction} or <code>null</code> if not found
    */
   FromInstruction getFrom();

   /**
    * The <code>MAINTAINER</code> allows you to set the Author field of the generated images.
    * 
    * @return a new instance of {@link MaintainerInstruction}
    */
   MaintainerInstruction maintainer();

   /**
    * The <code>MAINTAINER</code> allows you to set the Author field of the generated images *
    * 
    * @param name the maintainer name for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor maintainer(String name);

   /**
    * @return the current {@link MaintainerInstruction} or <code>null</code> if not found
    */
   MaintainerInstruction getMaintainer();

   /**
    * Remove the existing <code>MAINTAINER</code>.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeMaintainer();

   /**
    * The <code>RUN</code> instruction will execute any commands in a new layer on top of the current image and commit
    * the results. The resulting committed image will be used for the next step in the Dockerfile.
    * 
    * @return a new {@link RunInstruction} instance
    */
   RunInstruction run();

   /**
    * The <code>RUN</code> instruction will execute any commands in a new layer on top of the current image and commit
    * the results. The resulting committed image will be used for the next step in the Dockerfile.
    * 
    * @param parameters the parameters to be added in this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor run(String... parameters);

   /**
    * @return all <code>RUN</code> instructions or an empty {@link List} if not found
    */
   List<RunInstruction> getAllRun();

   /**
    * Remove all the existing <code>RUN</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllRun();

   /**
    * The main purpose of a <code>CMD</code> is to provide defaults for an executing container. These defaults can
    * include an executable, or they can omit the executable, in which case you must specify an <code>ENTRYPOINT</code>
    * instruction as well.
    * 
    * @return the current instance or a new instance of {@link CmdInstruction}
    */
   CmdInstruction cmd();

   /**
    * The main purpose of a <code>CMD</code> is to provide defaults for an executing container. These defaults can
    * include an executable, or they can omit the executable, in which case you must specify an <code>ENTRYPOINT</code>
    * instruction as well.
    * 
    * @param parameters the parameters to be added in this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor cmd(String... parameters);

   /**
    * @return the current {@link CmdInstruction} or <code>null</code> if not found
    */
   CmdInstruction getCmd();

   /**
    * Remove the current {@link CmdInstruction} if exists.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeCmd();

   /**
    * The <code>EXPOSE</code> instructions informs Docker that the container will listen on the specified network ports
    * at runtime. Docker uses this information to interconnect containers using links (see the Docker User Guide) and to
    * determine which ports to expose to the host when using the -P flag.
    * 
    * Note: <code>EXPOSE</code> doesn't define which ports can be exposed to the host or make ports accessible from the
    * host by default. To expose ports to the host, at runtime, use the -p flag or the -P flag.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   ExposeInstruction expose();

   /**
    * The <code>EXPOSE</code> instructions informs Docker that the container will listen on the specified network ports
    * at runtime. Docker uses this information to interconnect containers using links (see the Docker User Guide) and to
    * determine which ports to expose to the host when using the -P flag.
    * 
    * @param ports the ports to be exposed
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor expose(Integer... ports);

   /**
    * @return all <code>EXPOSE</code> instructions or an empty {@link List} if not found
    */
   List<ExposeInstruction> getAllExpose();

   /**
    * Remove all the existing <code>EXPOSE</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllExpose();

   /**
    * The <code>ENV</code> instruction sets the environment variable <b>key</b> to the value <b>value</b>. This value
    * will be passed to all future <code>RUN</code> instructions.
    * 
    * @return a new instance of {@link EnvInstruction}
    */
   EnvInstruction env();

   /**
    * The <code>ENV</code> instruction sets the environment variable <b>key</b> to the value <b>value</b>. This value
    * will be passed to all future <code>RUN</code> instructions.
    * 
    * @param key the env key
    * @param value the env value
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor env(String key, String value);

   /**
    * @return all <code>ENV</code> instructions or an empty {@link List} if not found
    */
   List<EnvInstruction> getAllEnv();

   /**
    * Remove all the existing <code>ENV</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllEnv();

   /**
    * The <code>ADD</code> instruction copies new files, directories or remote file URLs from <b>src</b> and adds them
    * to the filesystem of the container at the path <b>dest</b>.
    * 
    * @return a new ADD instance of {@link AddInstruction}
    */
   AddInstruction add();

   /**
    * The <code>ADD</code> instruction copies new files, directories or remote file URLs from <b>src</b> and adds them
    * to the filesystem of the container at the path <b>dest</b>.
    * 
    * @param source the source for this instruction
    * @param destination the destination for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor add(String source, String destination);

   /**
    * @return all <code>ADD</code> instructions or an empty {@link List} if not found
    */
   List<AddInstruction> getAllAdd();

   /**
    * Remove all the existing <code>ADD</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllAdd();

   /**
    * The <code>COPY</code> instruction copies new files or directories from <b>src</b> and adds them to the filesystem
    * of the container at the path <b>dest</b>.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   CopyInstruction copy();

   /**
    * The <code>COPY</code> instruction copies new files or directories from <b>src</b> and adds them to the filesystem
    * of the container at the path <b>dest</b>.
    * 
    * @param source the source for this instruction
    * @param destination the destination for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor copy(String source, String destination);

   /**
    * @return all <code>COPY</code> instructions or an empty {@link List} if not found
    */
   List<CopyInstruction> getAllCopy();

   /**
    * Remove all the existing <code>COPY</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllCopy();

   /**
    * An <code>ENTRYPOINT</code> allows you to configure a container that will run as an executable.
    * 
    * @return The existing {@link EntrypointInstruction} instruction or a new instance
    */
   EntrypointInstruction entrypoint();

   /**
    * An <code>ENTRYPOINT</code> allows you to configure a container that will run as an executable.
    * 
    * @param parameters the parameters to be added in this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor entrypoint(String... parameters);

   /**
    * @return The existing <code>ENTRYPOINT</code> instruction or null if not found
    */
   EntrypointInstruction getEntrypoint();

   /**
    * The <code>VOLUME</code> instruction will create a mount point with the specified name and mark it as holding
    * externally mounted volumes from native host or other containers.
    * 
    * @return The existing {@link EntrypointInstruction} instruction or a new instance
    */
   VolumeInstruction volume();

   /**
    * The <code>VOLUME</code> instruction will create a mount point with the specified name and mark it as holding
    * externally mounted volumes from native host or other containers.
    * 
    * @param name the volume name for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor volume(String name);

   /**
    * @return all <code>VOLUME</code> instructions or an empty {@link List} if not found
    */
   List<VolumeInstruction> getAllVolume();

   /**
    * Remove all the existing <code>VOLUME</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllVolume();

   /**
    * The <code>USER</code> instruction sets the user name or UID to use when running the image and for any
    * <code>RUN</code>, <code>CMD</code> and <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   UserInstruction user();

   /**
    * The <code>USER</code> instruction sets the user name or UID to use when running the image and for any
    * <code>RUN</code>, <code>CMD</code> and <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @param name the user name for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor user(String name);

   /**
    * @return The existing <code>USER</code> instruction or null if not found
    */
   UserInstruction getUser();

   /**
    * Remove the existing <code>USER</code> instruction.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeUser();

   /**
    * The <code>WORKDIR</code> instruction sets the working directory for any <code>RUN</code>, <code>CMD</code> and
    * <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   WorkdirInstruction workDir();

   /**
    * The <code>WORKDIR</code> instruction sets the working directory for any <code>RUN</code>, <code>CMD</code> and
    * <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @param path the path for this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor workDir(String path);

   /**
    * @return all <code>WORKDIR</code> instructions or an empty {@link List} if not found
    */
   List<WorkdirInstruction> getAllWorkDir();

   /**
    * Remove all <code>WORKDIR</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllWorkDir();

   /**
    * The <code>ONBUILD</code> instruction adds to the image a trigger instruction to be executed at a later time, when
    * the image is used as the base for another build. The trigger will be executed in the context of the downstream
    * build, as if it had been inserted immediately after the <code>FROM</code> instruction in the downstream
    * Dockerfile.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   OnBuildInstruction onBuild();

   /**
    * The <code>ONBUILD</code> instruction adds to the image a trigger instruction to be executed at a later time, when
    * the image is used as the base for another build. The trigger will be executed in the context of the downstream
    * build, as if it had been inserted immediately after the <code>FROM</code> instruction in the downstream
    * Dockerfile.
    * 
    * @param instruction the {@link DockerInstruction} to be added in this instruction
    * @return the current {@link DockerDescriptor} instance
    */
   <T extends DockerInstruction> T onBuild(Class<T> instruction);

   /**
    * @return all <code>ONBUILD</code> instructions or an empty {@link List} if not found
    */
   List<OnBuildInstruction> getAllOnBuild();

   /**
    * Remove all <code>ONBUILD</code> instructions.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllOnBuild();

   /**
    * @return a new {@link CommentInstruction}
    */
   CommentInstruction comment();

   /**
    * @param comment the comment to be added in this descriptor
    * @return the current {@link DockerDescriptor} instance
    */
   DockerDescriptor comment(String comment);

   /**
    * @return all comments from this {@link DockerDescriptor}
    */
   List<CommentInstruction> getAllComment();

   /**
    * Remove all comments from this {@link DockerDescriptor}
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllComment();

   /**
    * @return list of all {@link DockerInstruction}
    */
   List<DockerInstruction> getInstructions();

   /**
    * Add an instruction to this {@link DockerDescriptor}
    * 
    * @param instruction the instruction to be added
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor addInstruction(DockerInstruction instruction);
}
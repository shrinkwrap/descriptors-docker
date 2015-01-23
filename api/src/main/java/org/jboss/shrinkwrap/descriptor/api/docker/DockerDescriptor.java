/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
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
 * <p/>
 * <a href="https://docs.docker.com/reference/builder/">Dockerfile Reference</a>
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public interface DockerDescriptor extends Descriptor
{
   /**
    * @return list of all {@link DockerInstruction}
    */
   List<DockerInstruction> getInstructions();

   /**
    * The <code>FROM</code> instruction sets the Base Image for subsequent instructions. As such, a valid Dockerfile
    * must have <code>FROM</code> as its first instruction. The image can be any valid image – it is especially easy to
    * start by pulling an image from the Public Repositories.
    * 
    * @return a {@link FromInstruction} instance
    */
   FromInstruction from();

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
    * <p/>
    * Note: <code>EXPOSE</code> doesn't define which ports can be exposed to the host or make ports accessible from the
    * host by default. To expose ports to the host, at runtime, use the -p flag or the -P flag.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   ExposeInstruction expose();

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
    * 
    * The <code>COPY</code> instruction copies new files or directories from <b>src</b> and adds them to the filesystem
    * of the container at the path <b>dest</b>.
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   CopyInstruction copy();

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
    * @return The existing <code>ENTRYPOINT</code> instruction or null if not found
    */
   EntrypointInstruction getEntrypoint();

   /**
    * The VOLUME instruction will create a mount point with the specified name and mark it as holding externally mounted
    * volumes from native host or other containers.
    * 
    * @return The existing {@link EntrypointInstruction} instruction or a new instance
    */
   VolumeInstruction volume();

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
    * Creates a comment instruction
    */
   CommentInstruction comment();

   /**
    * Returns all comments from this {@link DockerDescriptor}
    */
   List<CommentInstruction> getAllComment();

   /**
    * Remove all comments from this {@link DockerDescriptor}
    * 
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor removeAllComment();
}
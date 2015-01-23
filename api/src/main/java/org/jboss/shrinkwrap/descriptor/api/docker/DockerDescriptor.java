/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.api.docker;

import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.Descriptor;
import org.jboss.shrinkwrap.descriptor.api.docker.instruction.DockerInstruction;

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
    * @return list of {@link DockerInstruction}
    */
   List<DockerInstruction> getInstructions();

   /**
    * The <code>FROM</code> instruction sets the Base Image for subsequent instructions. As such, a valid Dockerfile
    * must have <code>FROM</code> as its first instruction. The image can be any valid image – it is especially easy to
    * start by pulling an image from the Public Repositories.
    * 
    * @param image the image to be used in the <code>FROM</code> element
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor from(String image);

   /**
    * @return the <code>FROM</code> element
    */
   String getFrom();

   /**
    * The <code>MAINTAINER</code> allows you to set the Author field of the generated images.
    * 
    * @param from the <code>MAINTAINER</code> element
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor maintainer(String from);

   /**
    * @return the <code>MAINTAINER</code> element
    */
   String getMaintainer();

   /**
    * The <code>RUN</code> instruction will execute any commands in a new layer on top of the current image and commit
    * the results. The resulting committed image will be used for the next step in the Dockerfile.
    * 
    * @param command
    * @param parameters
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor run(String command, String... parameters);

   /**
    * The main purpose of a <code>CMD</code> is to provide defaults for an executing container. These defaults can
    * include an executable, or they can omit the executable, in which case you must specify an <code>ENTRYPOINT</code>
    * instruction as well.
    * 
    * @param command
    * @param parameters
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor cmd(String command, String... parameters);

   /**
    * The <code>EXPOSE</code> instructions informs Docker that the container will listen on the specified network ports
    * at runtime. Docker uses this information to interconnect containers using links (see the Docker User Guide) and to
    * determine which ports to expose to the host when using the -P flag.
    * <p/>
    * Note: <code>EXPOSE</code> doesn't define which ports can be exposed to the host or make ports accessible from the
    * host by default. To expose ports to the host, at runtime, use the -p flag or the -P flag.
    * 
    * @param ports
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor expose(int... ports);

   /**
    * The <code>ENV</code> instruction sets the environment variable <b>key</b> to the value <b>value</b>. This value
    * will be passed to all future <code>RUN</code> instructions.
    * 
    * @param key
    * @param value
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor env(String key, String value);

   /**
    * The <code>ADD</code> instruction copies new files, directories or remote file URLs from <b>src</b> and adds them
    * to the filesystem of the container at the path <b>dest</b>.
    * 
    * @param src
    * @param dest
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor add(String src, String dest);

   /**
    * 
    * The <code>COPY</code> instruction copies new files or directories from <b>src</b> and adds them to the filesystem
    * of the container at the path <b>dest</b>.
    * 
    * @param src
    * @param dest
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor copy(String src, String dest);

   /**
    * An <code>ENTRYPOINT</code> allows you to configure a container that will run as an executable.
    * 
    * @param cmd
    * @param parameters
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor entrypoint(String cmd, String... parameters);

   /**
    * The VOLUME instruction will create a mount point with the specified name and mark it as holding externally mounted
    * volumes from native host or other containers.
    * 
    * @param volume
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor volume(String volume, String... additionalVolumes);

   /**
    * The <code>USER</code> instruction sets the user name or UID to use when running the image and for any
    * <code>RUN</code>, <code>CMD</code> and <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @param user
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor user(String user);

   /**
    * The <code>WORKDIR</code> instruction sets the working directory for any <code>RUN</code>, <code>CMD</code> and
    * <code>ENTRYPOINT</code> instructions that follow it in the Dockerfile.
    * 
    * @param workdir
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor workdir(String workdir);

   /**
    * The <code>ONBUILD</code> instruction adds to the image a trigger instruction to be executed at a later time, when
    * the image is used as the base for another build. The trigger will be executed in the context of the downstream
    * build, as if it had been inserted immediately after the <code>FROM</code> instruction in the downstream
    * Dockerfile.
    * 
    * @param instruction
    * @return the current instance of {@link DockerDescriptor}
    */
   DockerDescriptor onbuild(DockerInstruction instruction);
}

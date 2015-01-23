/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.impl.docker;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jboss.shrinkwrap.descriptor.api.DescriptorExportException;
import org.jboss.shrinkwrap.descriptor.api.DescriptorExporter;
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
import org.jboss.shrinkwrap.descriptor.impl.docker.instructions.DockerInstructions;
import org.jboss.shrinkwrap.descriptor.spi.DescriptorImplBase;

/**
 * Implementation of the {@link DockerDescriptor} interface
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class DockerDescriptorImpl extends DescriptorImplBase<DockerDescriptor> implements DockerDescriptor
{

   private final List<DockerInstruction> instructions = new ArrayList<>();

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

   @Override
   public List<DockerInstruction> getInstructions()
   {
      return instructions;
   }

   @Override
   public FromInstruction from()
   {
      FromInstruction from = getFrom();
      if (from == null)
      {
         from = DockerInstructions.FROM.create(this);
         instructions.add(from);
      }
      return from;
   }

   @Override
   public FromInstruction getFrom()
   {
      return findFirst(FromInstruction.class);
   }

   @Override
   public MaintainerInstruction maintainer()
   {
      MaintainerInstruction maintainer = getMaintainer();
      if (maintainer == null)
      {
         maintainer = DockerInstructions.MAINTAINER.create(this);
         instructions.add(maintainer);
      }
      return maintainer;
   }

   @Override
   public MaintainerInstruction getMaintainer()
   {
      return findFirst(MaintainerInstruction.class);
   }

   @Override
   public DockerDescriptor removeMaintainer()
   {
      removeAll(MaintainerInstruction.class);
      return this;
   }

   @Override
   public RunInstruction run()
   {
      RunInstruction run = DockerInstructions.RUN.create(this);
      instructions.add(run);
      return run;
   }

   @Override
   public List<RunInstruction> getAllRun()
   {
      return findAll(RunInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllRun()
   {
      removeAll(RunInstruction.class);
      return this;
   }

   @Override
   public CmdInstruction cmd()
   {
      CmdInstruction cmd = getCmd();
      if (cmd == null)
      {
         cmd = DockerInstructions.CMD.create(this);
         instructions.add(cmd);
      }
      return cmd;
   }

   @Override
   public CmdInstruction getCmd()
   {
      return findFirst(CmdInstruction.class);
   }

   @Override
   public DockerDescriptor removeCmd()
   {
      removeAll(CmdInstruction.class);
      return this;
   }

   @Override
   public ExposeInstruction expose()
   {
      ExposeInstruction expose = DockerInstructions.EXPOSE.create(this);
      instructions.add(expose);
      return expose;
   }

   @Override
   public List<ExposeInstruction> getAllExpose()
   {
      return findAll(ExposeInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllExpose()
   {
      removeAll(ExposeInstruction.class);
      return this;
   }

   @Override
   public EnvInstruction env()
   {
      EnvInstruction env = DockerInstructions.ENV.create(this);
      instructions.add(env);
      return env;
   }

   @Override
   public List<EnvInstruction> getAllEnv()
   {
      return findAll(EnvInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllEnv()
   {
      removeAll(EnvInstruction.class);
      return this;
   }

   @Override
   public AddInstruction add()
   {
      AddInstruction add = DockerInstructions.ADD.create(this);
      instructions.add(add);
      return add;
   }

   @Override
   public List<AddInstruction> getAllAdd()
   {
      return findAll(AddInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllAdd()
   {
      removeAll(AddInstruction.class);
      return this;
   }

   @Override
   public CopyInstruction copy()
   {
      CopyInstruction copy = DockerInstructions.COPY.create(this);
      instructions.add(copy);
      return copy;
   }

   @Override
   public List<CopyInstruction> getAllCopy()
   {
      return findAll(CopyInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllCopy()
   {
      removeAll(CopyInstruction.class);
      return this;
   }

   @Override
   public EntrypointInstruction entrypoint()
   {
      EntrypointInstruction entrypoint = getEntrypoint();
      if (entrypoint == null)
      {
         entrypoint = DockerInstructions.ENTRYPOINT.create(this);
         instructions.add(entrypoint);
      }
      return entrypoint;
   }

   @Override
   public EntrypointInstruction getEntrypoint()
   {
      return findFirst(EntrypointInstruction.class);
   }

   @Override
   public VolumeInstruction volume()
   {
      VolumeInstruction volume = DockerInstructions.VOLUME.create(this);
      instructions.add(volume);
      return volume;
   }

   @Override
   public List<VolumeInstruction> getAllVolume()
   {
      return findAll(VolumeInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllVolume()
   {
      removeAll(VolumeInstruction.class);
      return this;
   }

   @Override
   public UserInstruction user()
   {
      UserInstruction user = getUser();
      if (user == null)
      {
         user = DockerInstructions.USER.create(this);
         instructions.add(user);
      }
      return user;
   }

   @Override
   public UserInstruction getUser()
   {
      return findFirst(UserInstruction.class);
   }

   @Override
   public DockerDescriptor removeUser()
   {
      removeAll(UserInstruction.class);
      return this;
   }

   @Override
   public WorkdirInstruction workDir()
   {
      WorkdirInstruction workdir = DockerInstructions.WORKDIR.create(this);
      instructions.add(workdir);
      return workdir;
   }

   @Override
   public List<WorkdirInstruction> getAllWorkDir()
   {
      return findAll(WorkdirInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllWorkDir()
   {
      removeAll(WorkdirInstruction.class);
      return this;
   }

   @Override
   public OnBuildInstruction onBuild()
   {
      OnBuildInstruction onBuild = DockerInstructions.ONBUILD.create(this);
      instructions.add(onBuild);
      return onBuild;
   }

   @Override
   public List<OnBuildInstruction> getAllOnBuild()
   {
      return findAll(OnBuildInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllOnBuild()
   {
      removeAll(OnBuildInstruction.class);
      return this;
   }

   @Override
   public CommentInstruction comment()
   {
      CommentInstruction comment = DockerInstructions.COMMENT.create(this);
      instructions.add(comment);
      return comment;
   }

   @Override
   public List<CommentInstruction> getAllComment()
   {
      return findAll(CommentInstruction.class);
   }

   @Override
   public DockerDescriptor removeAllComment()
   {
      removeAll(CommentInstruction.class);
      return this;
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor#addInstruction(org.jboss.shrinkwrap.descriptor.api
    * .docker.instruction.DockerInstruction)
    */
   @Override
   public DockerDescriptor addInstruction(DockerInstruction instruction)
   {
      this.instructions.add(instruction);
      return this;
   }

   @SuppressWarnings("unchecked")
   private <T extends DockerInstruction> T findFirst(Class<T> type)
   {
      for (DockerInstruction instruction : instructions)
      {
         if (type.isInstance(instruction))
         {
            return (T) instruction;
         }
      }
      return null;
   }

   private void removeAll(Class<? extends DockerInstruction> type)
   {
      Iterator<DockerInstruction> it = instructions.iterator();
      while (it.hasNext())
      {
         DockerInstruction next = it.next();
         if (type.isInstance(next))
         {
            it.remove();
         }
      }
   }

   @SuppressWarnings("unchecked")
   private <T extends DockerInstruction> List<T> findAll(Class<T> type)
   {
      List<T> result = new ArrayList<>();
      Iterator<DockerInstruction> it = instructions.iterator();
      while (it.hasNext())
      {
         DockerInstruction next = it.next();
         if (type.isInstance(next))
         {
            result.add((T) next);
         }
      }
      return result;
   }
}

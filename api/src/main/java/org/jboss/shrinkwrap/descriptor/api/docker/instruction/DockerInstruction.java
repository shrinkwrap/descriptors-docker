/**
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.shrinkwrap.descriptor.api.docker.instruction;

import java.io.PrintWriter;

import org.jboss.shrinkwrap.descriptor.api.Child;
import org.jboss.shrinkwrap.descriptor.api.docker.DockerDescriptor;

/**
 * Base interface for the instructions
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public interface DockerInstruction extends Child<DockerDescriptor>
{
   void read(String line);

   void export(PrintWriter pw);
}

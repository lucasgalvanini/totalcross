
#include "Runtime.h"
#include "cpproc.h"
#include <sys/types.h>

TC_API void jlR_exec_SSs(NMParams p) {
    int fds[CPIO_EXEC_NUM_PIPES];
    TCObject cmd = p->obj[1];
    TCObject envp = p->obj[2];
    TCObject dirPath = p->obj[3];
    TCObject fileChannelIn;
    TCObject fileChannelOut;
    TCObject fileChannelErr;
    TCObject fileInputStream;
    TCObject proccess;
    // Method getPath = getMethod(OBJ_CLASS(file), false, "getPath", 0);
    // TValue filePathValue = executeMethod(mainContext, getPath, file);
    // TCObject filePath = filePathValue.asObj;
    char *cmdArray = String2CharP(cmd);
    char *envpArray = String2CharP(envp);
    char *filePathArray = String2CharP(dirPath);
    int cmdArrayLen = strlen(cmdArray);
    int envpArrayLen = strlen(envpArray);
    int filePathArrayLen = strlen(filePathArray);
    char* strings;
    int i;
    int num_strings = 0;
    char **newEnviron = NULL;
    int pipe_count = 3;
    pid_t pid = -1;
    int err;

    if((strings = malloc (((cmdArrayLen + 1)
			  + (envpArray != NULL ? envpArrayLen + 1 : 0)
			  + (filePathArray !=
			     NULL ? 1 : 0)) * sizeof (*strings))) == NULL)
    {
        //error message here
    }
    for (i = 0; i < cmdArrayLen; i++)
    {
      if ((strings[num_strings++] = cmdArray[i]) == NULL)
	    {
            //done here?
        }
    }
    strings[num_strings++] = NULL;
    if (envpArray != NULL)
    {
        newEnviron = strings + num_strings;
        for (i = 0; i < envpArrayLen; i++)
        {
            if ((strings[num_strings++] = envpArray[i]) == NULL)
            {
                //done here?
            }
        }
        strings[num_strings++] = NULL;	/* terminate array with NULL */
    }
    err = cpproc_forkAndExec(strings, newEnviron, fds, pipe_count, &pid, filePathArray);
    if(err != 0) 
    {
        //error message
        printf("erro %d", err);
    }
    
    fileChannelIn = createObject(p->currentContext, "java.nio.channels.FileChannelImpl");
    if(fileChannelIn == NULL) {
        //nothing here
    }
    FileChannelImpl_nfd(fileChannelIn) = fds[1];
    fileInputStream = createObject(p->currentContext, "java.io.FileInputStream");
    FileInputStream_fileChannel(fileInputStream) = fileChannelIn;
    if(fileInputStream == NULL) {
        //nothing here(for now)
    }
    proccess = createObject(p->currentContext, "java.lang.Proccess");
    Proccess_inputStream(proccess) = fileInputStream;
    p->retO = proccess;
}
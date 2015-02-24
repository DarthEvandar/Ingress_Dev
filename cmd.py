import subprocess
import os
def commandLoop():
    command = raw_input('C:\\')
    #os.system(command)
    try:       
        s = subprocess.check_output(command, shell=True)
        print s
        #Write output to text file
        f = open('tree.txt', 'w')
        #f.write(subprocess.check_output(command, shell=True))
    except subprocess.CalledProcessError as e:
        print e.returncode
        print e.output
    commandLoop();
    return
commandLoop();

import os

def openCurrentFileNRows(filePath, nrow):
    if os.path.exists(filePath):
        with open(filePath, 'r') as f:
            str = ""
            for line in f:
                if nrow == 0:
                    break
                str = str + line
                nrow = nrow - 1
            print("first %d rows are:\n %s" % (nrow,str))

print('package __name__ is:',__name__)
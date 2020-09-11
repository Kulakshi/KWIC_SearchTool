# KWIC_SearchTool

KWIC search tool is a simple implementation for Key Word In Context text search. 

## Developer guide
This is implemented as a netbeans project


 ## Current capabilities
- Add files (as files or folders) initially and over and over again - the system checks for duplicate files and remove them
- Search for a word with a window size to see the context
- Get results on left and right side contexts, containing file name and sentence index

## Limitations
- Both words and punctuations are considered as nodes. So that punctuation marks have a space around them like a word. When calculating the window size, punctuations are also counted.
- The application hangs when trying to load some XML files. For example, Text/C/C9/C92.xml


## To do
- Fix the application hang error mentioned above
- Add file updating capability (user can check the already added files and add/remove these files)
- Use more efficient file reading and searching algorithms
- Reduce memory usage by using hash values instead of Strings
- Enhance UI/UX 
- Add save search results capability


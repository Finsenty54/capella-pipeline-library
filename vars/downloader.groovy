def downloadWindowsJDK(jdkWinFolder) {
	def jdk = 'jdk-14.0.2'
  
  def jdkWinURL = 'https://download.java.net/java/GA/jdk14.0.2/205943a0976c4ed48cb16f1043c5c647/12/GPL/openjdk-14.0.2_windows-x64_bin.zip'
  def jdkWinZip = 'jdkWin.zip'	
      
	sh "curl -k -o ${jdkWinZip} ${jdkWinURL}"
	sh "unzip -q ${jdkWinZip} -d ${jdkWinFolder}"
	sh "mv ${jdkWinFolder}/${jdk} ${jdkWinFolder}/jre"        
  
  println "Windows JDK dowloaded to ${jdkWinFolder}/jre"
  sh "ls ${jdkWinFolder}/jre"
}

def downloadLinuxJDK(jdkLinuxFolder) {
  def jdk = 'jdk-14.0.2'
  
  def jdkLinuxURL = 'https://download.java.net/java/GA/jdk14.0.2/205943a0976c4ed48cb16f1043c5c647/12/GPL/openjdk-14.0.2_linux-x64_bin.tar.gz'
  def jdkLinuxTar = 'jdkLinux.tar.gz'
      
	sh "curl -k -o ${jdkLinuxTar} ${jdkLinuxURL}"
	sh "mkdir ${jdkLinuxFolder}"
  sh "tar xzf ${jdkLinuxTar} -C ${jdkLinuxFolder}"
	sh "mv ${jdkLinuxFolder}/${jdk} ${jdkLinuxFolder}/jre"
  
  println "Linux JDK downloaded to ${jdkLinuxFolder}/jre"    
	sh "ls ${jdkLinuxFolder}/jre"
}

def downloadMacJDK(jdkMacFolder) {
  def jdk = 'jdk-14.0.2'
  
  def jdkMacURL = 'https://download.java.net/java/GA/jdk14.0.2/205943a0976c4ed48cb16f1043c5c647/12/GPL/openjdk-14.0.2_osx-x64_bin.tar.gz'
  def jdkMacTar = 'jdkMac.tar.gz'
        
	sh "curl -k -o ${jdkMacTar} ${jdkMacURL}"
	sh "mkdir ${jdkMacFolder}"
	sh "tar xzf ${jdkMacTar} -C ${jdkMacFolder}"
	sh "mv ${jdkMacFolder}/${jdk}.jdk ${jdkMacFolder}/jre"

  println "Mac JDK downloaded to ${jdkMacFolder}/jre"
	sh "ls ${jdkMacFolder}/jre"
}


def fetchTemurinJDK17(name, os) {
    def jdkArchive = ''
    def jdkURL = ''
    
    switch (os) {
        case 'win':
            jdkURL = 'https://api.adoptium.net/v3/binary/latest/17/ga/windows/x64/jre/hotspot/normal/eclipse'
            jdkArchive = name + '-' + os + '.zip'
            break;
        case 'mac':
            jdkURL = 'https://api.adoptium.net/v3/binary/latest/17/ga/mac/x64/jre/hotspot/normal/eclipse'
            jdkArchive = name + '-' + os + '.tar.gz'
            break;
        case 'linux':
            jdkURL = 'https://api.adoptium.net/v3/binary/latest/17/ga/linux/x64/jre/hotspot/normal/eclipse'
            jdkArchive = name + '-' + os + '.tar.gz'
            break;
        default:
            return;
    }
    
    sh "curl -L -k -o ${jdkArchive} ${jdkURL}"
    sh "ls -la ${jdkArchive}"
}


/**
 * Download Temurin JDK 17 version in <code>jdkFolder</code> folder for operating system <code>os</code>
 * @param jdkFolder
 * @param os The operating system. Expected one of 'win', 'linux', 'mac'
 * @return Nothing but extracts the downloaded JDK to <code>${jdkFolder}/jre</code>.
 */
def downloadTemurinJDK17(jdkFolder, os) {
    
    def jdkArchive = ''
    def jdkURL = ''
    
    switch (os) {
        case 'win':
            jdkURL = 'https://ci.eclipse.org/capella/job/prefetch-java/lastSuccessfulBuild/artifact/jre17-win.zip'
            jdkArchive = 'jre17-win.zip'
            break;
        case 'mac':
            jdkURL = 'https://ci.eclipse.org/capella/job/prefetch-java/lastSuccessfulBuild/artifact/jre17-mac.tar.gz'
            jdkArchive = 'jre17-mac.tar.gz'
            break;
        case 'linux':
            jdkURL = 'https://ci.eclipse.org/capella/job/prefetch-java/lastSuccessfulBuild/artifact/jre17-linux.tar.gz'
            jdkArchive = 'jre17-linux.tar.gz'
            break;
        default:
            return;
    }
    
	
//     sh "curl -L -k -o ${jdkArchive} ${jdkURL}"
    // get from local position
//     sh "cp /home/zentrei/Documents/${jdkArchive} ./"
    sh "ls -la ${jdkArchive}"
    
    switch (os) {
        case 'win':
            sh "unzip -q ${jdkArchive} -d ${jdkFolder}"
            break;
        case 'mac':
            sh "mkdir ${jdkFolder}"
            sh "tar xzf ${jdkArchive} -C ${jdkFolder}"
            break;
        case 'linux':
            sh "mkdir ${jdkFolder}"
            sh "tar xzf ${jdkArchive} -C ${jdkFolder}"
            break;
        default:
            break;
    }
    
    sh "mv ${jdkFolder}/jdk* ${jdkFolder}/jre"
    println "${os} JDK downloaded to ${jdkFolder}/jre"
    sh "ls ${jdkFolder}/jre"
    
}

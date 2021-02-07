# Install JDK and Nodejs

## Linux

```shell
export GRAALVM_VERSION=21.0.0
export GRAALVM_NAME=graalvm-ce-java11-linux-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

wget $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_NAME.tar.gz
tar -xvzf $GRAALVM_NAME.tar.gz
sudo mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=/usr/local/lib/$GRAALVM_LIB/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:\$PATH" >> ~/.bashrc
source ~/.bashrc
```

## macOS

```shell
export GRAALVM_VERSION=21.0.0
export GRAALVM_NAME=graalvm-ce-java11-darwin-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

curl -L $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_PACKAGE.tar.gz \
     -o $GRAALVM_PACKAGE.tar.gz
tar -xvzf $GRAALVM_PACKAGE.tar.gz
sudo mv $GRAALVM_LIB /Library/Java/JavaVirtualMachines/
echo "export PATH=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:\$PATH" \
     >> ~/.bash_profile
echo "export JAVA_HOME=/Library/Java/JavaVirtualMachines/$GRAALVM_LIB/Contents/Home" \
     >> ~/.bash_profile
source ~/.bash_profile
```

## Windows

Firstly, you need to install following tools:
- `cygwin` download from https://cygwin.com/
- `7zip` download from https://www.7-zip.org/ and install to `C:\Program Files\7-Zip\`

Then open `mintty.exe`, usually automatically appear in your desktop after installing `cygwin`:

```shell
export GRAALVM_VERSION=21.0.0
export GRAALVM_PACKAGE=graalvm-ce-java11-windows-amd64-$GRAALVM_VERSION
export GRAALVM_LIB=graalvm-ce-java11-$GRAALVM_VERSION
export GRAALVM_GITHUB=https://github.com/graalvm/graalvm-ce-builds/releases/download

wget $GRAALVM_GITHUB/vm-$GRAALVM_VERSION/$GRAALVM_PACKAGE.zip
"/cygdrive/C/Program Files/7-Zip/7z.exe" x $GRAALVM_PACKAGE.zip
mv $GRAALVM_LIB /usr/local/lib
echo "export PATH=\"/usr/local/lib/$GRAALVM_LIB/bin:/usr/local/lib/$GRAALVM_LIB/languages/js/bin:\$PATH\"" >> ~/.bash_profile
source ~/.bash_profile
```

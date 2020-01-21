echo get version
VERSION=2.9
echo v${VERSION}
git add .
git pull
git commit -a -m "v${VERSION}"
git push
#npm version patch
#npm publish
#./npm_publish.sh
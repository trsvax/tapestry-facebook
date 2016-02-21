
//Copyright [2011] [Barry Books]

//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at

//       http://www.apache.org/licenses/LICENSE-2.0

//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.


This Tapestry component library implements the Facebook XFBML async javascript library. It will also write OpenGraph meta tags.

Currently to use the components you need to build the source then add the following to your pom file.

     <dependency>
    	<groupId>com.trsvax</groupId>
    	<artifactId>tapestry-facebook</artifactId>
    	<version>0.0.1-SNAPSHOT</version>
    </dependency>


To use the tags on a page you'll need at least the <t:fb.asyncInit t:id="fbinit"/> element in your page/layout file and
something like the following in your java file.

    @Property
    private FBInit fbInit;
    
    @SetupRender
    void setupRender() {
    	fbInit = new FBInit();
    	fbInit.setAppID("your facebook app id");
    	fbInit.setCookie(true);
    	fbInit.setStatus(true);
    }
   
Then

<t:fb.like />

which by default has the following events

	void onEdgeCreate(@RequestParameter("url") String url) {
		logger.info("edge create {}",url);
	}
	
	void onEdgeRemove(@RequestParameter("url") String url) {
		logger.info("edge remove {}",url);
	}
	
If you want OpenGraph tags

@Environmental
private FBAsyncSupport fbAsyncSupport;

@SetupRender
void setupRender() {
	Tags tags = new Tags();
	tags.setSomeStuff();
	fbAsyncSupport.setTags(tags);
}

    

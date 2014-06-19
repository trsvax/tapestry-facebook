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

package com.trsvax.tapestry.facebook.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.services.LibraryMapping;

public class FBModule
{

//	public static void bind(ServiceBinder binder) {}

	/**
	 * Contribute the library mapping for making components accessible with fb prefix
	 */
	public static void contributeComponentClassResolver(
				Configuration<LibraryMapping> configuration)
	{
		configuration.add(new LibraryMapping("fb", "com.trsvax.tapestry.facebook"));
	}

}

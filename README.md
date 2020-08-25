# Android View Binding

## Intro
A lightweight library aiming to speed up Android app development by leveraging the new [Android Data Binding](http://developer.android.com/tools/data-binding/guide.html) and taking the best from the [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) design pattern.

### Why should I use it?
1. **Data Binding**
 Android Data Binding is great and if you're not, you should start using it today.
2. **You don't need to care about screen rotation (configuration change) at all.**
 Most of the screen lifecycle is moved to ViewModel where the lifecycle is dramatically easier to understand and to use. The **ViewModel instance outlives it's Activity/Fragment** during configuration change so no more hassle with `onSaveInstanceState()` or using retained Fragments.
3. **ViewModel as the only variable in the layout**
 ViewModel serves as the data provider in layout's binding as well as handler for click or other methods common fro Data Binding. With a construct like `android:onClick="@{viewBinding.onClickedPlayButton}"` **you will never have to set an `OnClickListener` anymore**. Also, each ViewModel extends `BaseObservable` so you have a choice between using BaseObservable approach or ObservableField approach within the DataBinding. (see [Data Binding Guide](http://developer.android.com/tools/data-binding/guide.html))

### How does it work?
The framework extensively uses Java Generics to provide a type-safe link between Activity/Fragment and ViewModel and its binding.

ViewModel instances are stored in a global static Map and reattached automatically to corresponding Activity/Fragment. When there is no need for the ViewModel anymore (Activity finished) the instance is destroyed.

### 1. Depend on our library

View Binding for Android is available through Google's Maven Repository.
To use it:

1.  Open the `build.gradle` file for your application.
2.  Make sure that the `repositories` section includes Google's Maven Repository
    `google()`. For example:

    ```groovy
      allprojects {
        repositories {
          google()
          jcenter()
        }
      }
    ```

3.  Add the library to the `dependencies` section:

    ```groovy
      dependencies {
        // ...
        implementation 'com.zeoflow:view-binding:<version>'
        // ...
      }
    ```

### 2. Activity/Fragment Class
`MainActivity.java`

```java
public class MainActivity extends BindAppActivity<ActivityMainBinding, MainViewBinding>
{
    //..
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        //..
        setupViewBinding(R.layout.activity_main, MainViewBinding.class);
        //..
        super.onCreate(savedInstanceState);
        //..
        MainViewBinding mMainViewBinding = getViewBinding();
        //..
    }
    //..
}
```

### 3. Activity/Fragment Layout
`activity_main.xml`

```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
	xmlns:tools="http://schemas.android.com/tools" xmlns:app="http://schemas.android.com/apk/res-auto">

	<data>

        <variable
            name="viewBinding"
            type="com.zeoflow.view.binding.sample.MainViewBinding" />
	</data>

	<LinearLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		android:padding="@dimen/activity_padding"
		android:orientation="vertical">

		<android.support.design.widget.TextInputLayout
			android:layout_width="match_parent"
			android:layout_height="wrap_content">

			<EditText
				android:layout_width="match_parent"
				android:layout_height="wrap_content"
				android:text="@={viewBinding.name}"
				android:inputType="textPersonName|textCapWords"
				android:hint="@string/hint_enter_your_name" />
		</android.support.design.widget.TextInputLayout>


		<FrameLayout
			android:layout_width="match_parent"
			android:layout_height="0dp"
			android:layout_weight="1"
			android:animateLayoutChanges="true">

			<TextView
				android:layout_width="wrap_content"
				android:layout_height="wrap_content"
				android:layout_gravity="center"
				android:textAppearance="@style/Base.TextAppearance.AppCompat.Headline"
				android:textColor="@color/colorPrimary"
				android:text="@{@string/hello(viewBinding.name)}"
				app:show="@{viewBinding.name != null &amp;&amp; !viewBinding.name.empty}"
				tools:text="@string/hello" />
		</FrameLayout>


		<Button
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"
			android:layout_gravity="center"
			android:onClick="@{() -> viewBinding.showDialog()}"
			android:text="@string/button_dialog_fragment"
			style="@style/Widget.AppCompat.Button.Colored" />
	</LinearLayout>
</layout>

```

### 4. ViewModel
`MainViewModel.java`

```java
public class MainViewBinding extends ViewBinding
{

	public final ObservableField<String> name = new ObservableField<>();

	@Override
	public void onViewModelCreated()
	{
		super.onViewModelCreated();
		// Do API calls etc.
	}

	@Override
	public void onViewAttached(boolean firstAttachment)
	{
		super.onViewAttached(firstAttachment);
		// manipulate with the view
	}
}
```

## License
    Copyright 2020 ZeoFlow
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

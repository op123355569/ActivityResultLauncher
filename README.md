# ActivityResultLauncher
 基于官方api，开发的代替startActivityForResult和requestPermission，这两个方法是常用的，官方还提供了其他的api，可根据需要自行继承[BaseActivityResultLauncher](https://github.com/op123355569/ActivityResultLauncher/blob/main/library/src/main/java/com/hg/activityresultlauncher/BaseActivityResultLauncher.kt)
 
 [![](https://jitpack.io/v/op123355569/ActivityResultLauncher.svg)](https://jitpack.io/#op123355569/ActivityResultLauncher)
 
 在根目录的build.gradle添加如下代码：
 ```
 allprojects {
    repositories {
        // ...
        maven { url 'https://www.jitpack.io' }
    }
}
 ```
 
 在工程目录的build.gradle添加如下代码：
 ```
 dependencies {
    implementation 'com.github.op123355569:ActivityResultLauncher:lastVersion'
}
 ```
 
 Java用法：
 ```
 // StartActivityForResult
 ProxyStartActivityForResult startActivityForResult1 = new ProxyStartActivityForResult(this);
 startActivityForResult1.launch(new Intent(JavaActivity.this, ResultActivity.class), result -> {
     if (result != null) {
         int resultCode = result.getResultCode();
         String name = "";
         int age = 0;

         if (result.getData() != null) {
             name = result.getData().getStringExtra("name");
             age = result.getData().getIntExtra("age", age);
         }

         Log.e("Java", "ResultCode:" + resultCode + ",name:" + name + ",age:" + age);
     }
 });
            
 // RequestPermission
 ProxyRequestPermission requestPermission1 = new ProxyRequestPermission(this);
 requestPermission1.launch(
     (result, isAgreeAll) -> Log.e("Java", "权限是否全部同意：" + isAgreeAll),
     Manifest.permission.CAMERA
 ));
 ```
 
 Kotlin用法：
 ```
 // StartActivityForResult
 val startActivityForResult1 = ProxyStartActivityForResult(this)
 startActivityForResult1.launch(Intent(this, ResultActivity::class.java), object : OnActivityResultListener {
     override fun onActivityResult(result: ActivityResult?) {
         val resultCode = result?.resultCode
         val name = result?.data?.getStringExtra("name")
         val age = result?.data?.getIntExtra("age", 0)
         Log.e("Kotlin", "ResultCode:$resultCode,name:$name,age:$age")
     }
 })
 
 // RequestPermission
 requestPermission1 = ProxyRequestPermission(this)
 requestPermission1.launch(
     object : OnRequestPermissionsResultListener {
         override fun onRequestPermissionsResult(result: Map<String, Boolean>, isAgreeAll: Boolean) {
             Log.e("Kotlin", "权限是否全部同意：$isAgreeAll")
         }
     },
     Manifest.permission.CAMERA
 )
 ```
 
具体用法Demo中有写。

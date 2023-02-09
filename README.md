# downwardapi_test
 downward api volumes update delay test

New create pod will automatically use default service account. The default service account does not have the patch permission to modify annotations.

So we can bind default service account to the cluster-admin cluster role to let the default service account get the whole permission, by use command below:

`kubectl create clusterrolebinding [yourclusterrolebindingname] --clusterrole=cluster-admin --serviceaccount=default:default`

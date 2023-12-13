var app = new Vue({
        el: '#app',
        data: {
            userData: {}, // 用于存储用户数据
            post: [],
            comments: [],
            newPostData: {
                title: '',
                content: '',
            },
            onlineUsers: [],
            showDialog: false,
            isExpanded: false
        },
        mounted: function () {
            axios.get('http://localhost:8080/users/onlineUsers').then((response) => {
                if (response.data.code === 20011) {
                    console.log(response.data.data)
                    this.onlineUsers = response.data.data;
                } else {
                    this.$message.error('获取在线用户失败');
                }
            } ).catch((error) => {
                console.log(error);
            });
            axios.get('http://localhost:8080/users/isLogin').then((response) => {
                if (response.data.code === 20011) {
                    // 用户已登录
                    this.userData = response.data.data;
                    // 将用户数据存储到localStorage中
                    const expirationTime = new Date().getTime() + 1000 * 60 * 60 * 24 * 7;
                    const data = {
                        expirationTime: expirationTime,
                        userData: this.userData
                    };
                    localStorage.setItem('userData', JSON.stringify(data));
                } else {
                    //清空userData
                    this.userData = {};
                }
            }).catch((error) => {
                console.log(error);
            });

        var storedUserData = localStorage.getItem('userData');
        if(storedUserData) {
            const data = JSON.parse(storedUserData);
            const currentTime = new Date().getTime();
            if (currentTime < data.expirationTime) {
                // 数据未过期，将其设置给userData
                this.userData = data.userData;
            } else {
                // 数据已过期，删除它
                localStorage.removeItem('userData');
            }
        }

        // 获取文章列表
        axios.get('http://localhost:8080/posts').then((response) => {
            if (response.data.code === 20011) {
                this.post = response.data.data;
            } else {
                this.$message.error('获取文章失败');
            }
        }).catch((error) => {
            console.log(error);
        });
    },
    methods
:
{
    login: function () {
        // 跳转到登录页面
        window.location.href = "login.html";
    }
,
    out: function () {
        axios.get('http://localhost:8080/users/logout', {}).then((response) => {
            if (response.data.code === 20011) {
                this.$message.success('注销成功');
                //删除本地存储的用户数据
                localStorage.removeItem('userData');
                //刷新页面 2s后刷新
                setTimeout(function () {
                window.location.reload();
            }, 2000);
            } else {
                this.$message.error('注销失败');
            }
        }).catch((error) => {
            this.$message.error('请求失败，请稍后再试');
            console.log(error);
        });
    }
,
    newPost: function () {
        // 首先访问登录接口，如果返回的code是20011，说明用户已经登录，可以跳转到新建文章页面
        axios.get('http://localhost:8080/users/isLogin').then((response) => {
            if (response.data.code === 20011) {
                app.showDialog = true
            } else {
                this.$message.error('请先登录');
            }
        }).catch((error) => {
            console.log(error);
        });
    }
,
    showDia: function () {

        app.showDialog = false;
        // 清空数据
        this.newPostData = {
            title: '',
            content: ''
        };
    }
,
    createPost: function () {
        //不能为null
        if (app.newPostData.title === '') {
            this.$message.error('标题不能为空');
            return;
        }
        if (this.newPostData.content === '') {
            this.$message.error('内容不能为空');
            return;
        }
        //发送用户id 标题 内容
        axios.post('http://localhost:8080/posts', {
            user: {
                id: this.userData.id
            },
            title: this.newPostData.title,
            content: this.newPostData.content
        }).then((response) => {
            if (response.data.code === 20041) {
                this.$message({message: '创建成功', type: 'success'});
                //刷新页面
                window.location.reload();
            } else {
                this.$message.error('创建失败');
            }
        }).catch((error) => {
            this.$message.error('请求失败，请稍后再试');
            console.log(error);
        });
    }
,
    Details: function (postId) {
        // 这里的URL应该指向您的详情页URL
        // 假设详情页URL是 'details.html' 并且使用查询参数传递ID
        window.location.href = "details.html?id=" + postId;
    }
,
}
})
;
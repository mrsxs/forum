var app = new Vue({
    el: '#app',
    data: {
        userData: {},
        post: [],
        comments: [],
        comment: ""
    },
    mounted: function () {
        var storedUserData = localStorage.getItem('userData');
        if (storedUserData) {
            const data = JSON.parse(storedUserData);
            const currentTime = new Date().getTime();

            if (currentTime < data.expirationTime) {
                // ����δ���ڣ��������ø�userData
                this.userData = data.userData;
            } else {
                // �����ѹ��ڣ�ɾ����
                localStorage.removeItem('userData');
            }
        }
        // ��ȡURL�еĲ�ѯ����
        var queryParams = new URLSearchParams(window.location.search);
        var postId = queryParams.get('id'); // ��ȡid����
        // ʹ��postId�Ӻ�˻�ȡ��������...
        axios.get('http://localhost:8080/posts/' + postId).then((response) => {
            app.post = response.data.data;
        }).catch((error) => {
            console.log(error);
        });
        axios.get('http://localhost:8080/comments/' + postId).then((response) => {
            app.comments = response.data.data;
        }).catch((error) => {
            console.log(error);
        });
    },
    methods:{
        Reply: function () {
            axios.get('http://localhost:8080/users/isLogin').then((response) => {
                if (response.data.code === 20011) {
                    // User is logged in, proceed with the comment submission
                    axios.post('http://localhost:8080/comments', {
                        content: app.comment,
                        pid: app.post.id,
                        user: {
                            id: app.userData.id
                        },
                    }).then((response) => {
                        if (response.data.code === 20041) {
                            // Comment submission successful
                            this.$message({message: '�ظ��ɹ�', type: 'success'});
                            window.location.reload();//ˢ�µ�ǰҳ��.
                        } else {
                            // Handle other error cases if needed
                            this.$message({message: response.data.msg, type: 'error'});
                        }
                    }).catch((error) => {
                        console.log(error);
                    });
                } else {
                    // User is not logged in, show a message
                    this.$message.error('���ȵ�¼');
                }
            }).catch((error) => {
                console.log(error);
            });
        }
    }
})
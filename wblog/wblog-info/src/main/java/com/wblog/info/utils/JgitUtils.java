package com.wblog.info.utils;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

/**
 * @author luyanan
 * @since 2020/6/10
 * <p>使用jgit操作git仓库</p>
 **/

@Slf4j
public class JgitUtils {


    /**
     * <p>克隆项目</p>
     *
     * @param userName   用户名
     * @param passWord   密码
     * @param remotePath git地址
     * @param branch     分支
     * @param localPath  本地路径
     * @author luyanan
     * @since 2020/6/10
     */
    public static void gitClone(String userName, String passWord, String remotePath, String branch, String localPath) throws GitAPIException {
//设置远程服务器上的用户名和密码
        UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                UsernamePasswordCredentialsProvider(userName, passWord);

//克隆代码库命令
        CloneCommand cloneCommand = Git.cloneRepository();

        Git git = cloneCommand.setURI(remotePath) //设置远程URI
                .setBranch(branch) //设置clone下来的分支
                .setDirectory(new File(localPath)) //设置下载存放路径
                .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                .call();
        git.pull();
    }


    /**
     * <p>没有克隆,有则pull/p>
     *
     * @param userName   用户名
     * @param passWord   密码
     * @param remotePath git地址
     * @param branch     分支
     * @param localPath  本地路径
     * @author luyanan
     * @since 2020/6/10
     */
    public static void gitCloneOrPull(String userName, String passWord, String remotePath, String branch, String localPath) throws IOException, GitAPIException {


        Git git = null;
        File localFile = new File(localPath);
        if (localFile.exists() && new File(localFile + "/.git").exists()) {
            git = Git.open(localFile);
        } else {
//设置远程服务器上的用户名和密码
            UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new
                    UsernamePasswordCredentialsProvider(userName, passWord);

//克隆代码库命令
            CloneCommand cloneCommand = Git.cloneRepository();

            git = cloneCommand.setURI(remotePath) //设置远程URI
                    .setBranch(branch) //设置clone下来的分支
                    .setDirectory(new File(localPath)) //设置下载存放路径
                    .setCredentialsProvider(usernamePasswordCredentialsProvider) //设置权限验证
                    .call();

        }
        git.pull();
    }

    public static void main(String[] args) throws GitAPIException, IOException {

        gitCloneOrPull("", "", "https://github.com/wswenyue/note.git", "master", "D:/git/");
    }
}

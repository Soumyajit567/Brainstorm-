import React from "react";
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as GrIcons from 'react-icons/md';


export const SidebarData = [
    {
        title: 'Home',
        path: '/Home',
        icon: < AiIcons.AiFillHome /> ,
        cName: 'nav-text'
    },
    {
        title: 'About',
        path: '/About',
        icon: < AiIcons.AiFillInfoCircle /> ,
        cName: 'nav-text'
    },
    {
        title: 'Login',
        path: '/Login',
        icon: < AiIcons.AiOutlineLogin /> ,
        cName: 'nav-text'
    },
    {
        title: 'Register',
        path: '/Register',
        icon: < GrIcons.MdOutlineAppRegistration /> ,
        cName: 'nav-text'
    },
    {
        title: 'Announcements',
        path: './Announcements',
        icon: < GrIcons.MdAnnouncement /> ,
        cName: 'nav-text'
    },
    {
        title: 'Assignments',
        path: './Assignments',
        icon: < GrIcons.MdAssignmentTurnedIn /> ,
        cName: 'nav-text'
    },
    {
        title: 'Calendar',
        path: './Calendar',
        icon: < AiIcons.AiFillCalendar /> ,
        cName: 'nav-text'
    },
    {
        title: 'Courses',
        path: './Courses',
        icon: < AiIcons.AiTwotoneBook /> ,
        cName: 'nav-text'
    },
    {
        title: 'Users',
        path: './User',
        icon: < AiIcons.AiTwotoneBook /> ,
        cName: 'nav-text'
    },
]

---
layout: default
title: {{ site.name }}
---


<div id="home">
  {% for post in site.posts %}
      <h1 class="post-title">
          <a class="post-link" href="{{ post.url | relative_url }}">{{ post.title | escape }}{% if post.external-url %} &rarr;{% endif %}</a>
      </h1>
      <p class="post-meta">By {{ post.author }} on {{ post.date | date: "%b %-d, %Y" }}</p>
  {% endfor %}
</div>
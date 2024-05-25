FROM node:22

WORKDIR /app

COPY . .

RUN npm install

CMD [ "npx", "prisma", "migrate", "dev", "--name", "init" ]
